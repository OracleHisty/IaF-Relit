package com.github.alexthe666.iceandfire;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.zip.ZipInputStream;

public class JsonUnfucker {
    public static void main(String[] args) throws IOException {
        Map<String, TabulaModel> models = new HashMap<>();

        var gson = new GsonBuilder().setPrettyPrinting().create();

        Files.newDirectoryStream(Path.of("D:\\Git Repos\\IaF-Relit\\src\\main\\resources\\assets\\iceandfire\\models\\tabula\\icedragon")).forEach(new Consumer<Path>() {
            @Override
            public void accept(Path path) {
                if(path.toString().endsWith(".tbl")) {
                    try {
                        TabulaModel tabula;

                        ZipArchiveInputStream stream = new ZipArchiveInputStream(Files.newInputStream(path));

                        ZipArchiveEntry entry;
                        while ((entry = stream.getNextZipEntry()) != null) {
                            if(entry.getName().equals("model.json")) {
                                try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                                    byte[] buffer = new byte[1024];
                                    int len;
                                    while ((len = stream.read(buffer)) != -1) {
                                        os.write(buffer, 0, len);
                                    }

                                    tabula = gson.fromJson(os.toString(), TabulaModel.class);

                                    models.put(tabula.modelName, tabula);
                                    System.out.println();
                                }
                            }
                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Map<String, Map<String, TabulaModel.Cube>> cubeNames = new HashMap<>();

        models.forEach((s, tabulaModel) -> {
            var map = cubeNames.compute(s, (b, a) -> new HashMap<>());

            if(tabulaModel.cubes != null) {
                for(var cube : tabulaModel.cubes) {
                    traverse(cube, map);
                }
            }

            map.values().forEach(cube -> cube.children = null);
        });

        String target = "Ice_Dragon_Base_Male";

        var modelTarget = cubeNames.get(target);

        for(var name : cubeNames.keySet()) {

            var modelKeyFrame = cubeNames.get(name);

            var difference = new HashMap<String, Difference>();

            modelTarget.forEach((s, cube) -> {
                var frame = modelKeyFrame.get(s);

                if(frame == null) return;

                var diff = cube.difference(frame);

                if (diff != null) difference.put(s, diff);
            });

            if(!difference.isEmpty()) {
                Files.writeString(Path.of(name + ".json"), gson.toJson(difference));
            }
        }
    }

    public static void traverse(TabulaModel.Cube cube, Map<String, TabulaModel.Cube> map) {
        map.put(cube.name, cube);
        if(cube.children != null) {
            for(var child : cube.children) {
                traverse(child, map);
            }
        }
    }

    public static class TabulaModel {
        String modelName;
        String authorName;
        int projVersion;
        String[] metadata;
        int textureWidth;
        int textureHeight;
        float[] scale;
        String[] cubeGroups;
        Cube[] cubes;
        String[] anims;
        int cubeCount;

        public static class Cube {
            String name;
            float[] dimensions;
            float[] position;
            float[] offset;
            float[] rotation;
            float[] scale;
            int[] txOffset;
            boolean txMirror;
            float mcScale;
            float opacity;
            boolean hidden;
            Cube[] children;
            String identifier;

            public Difference difference(Cube frame) {
                float[] pos = null;
                float[] rot = null;
                float[] scale = null;

                if(!Arrays.equals(position, frame.position)) {
                    pos = frame.position;
                }

                if(!Arrays.equals(rotation, frame.rotation)) {
                    rot = frame.rotation;
                }

                if(!Arrays.equals(this.scale, frame.scale)) {
                    scale = frame.scale;
                }

                if(pos == null && rot == null && scale == null) return null;
                return new Difference(pos, rot, scale);
            }
        }
    }

    public record Difference(float[] pos, float[] rot, float[] scale) {

    }
}

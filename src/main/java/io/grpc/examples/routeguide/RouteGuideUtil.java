package io.grpc.examples.routeguide;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjinkun.
 * @date 15/11/22
 * @time 下午3:26
 * Common utilities for the RouteGuide demo.
 */
public class RouteGuideUtil {
    private static final double COORD_FACTOR = 1e7;

    /**
     * Gets the latitude for the given point.
     *
     * @param location
     * @return
     */
    public static double getLatitude(Point location) {
        return location.getLatitude() / COORD_FACTOR;
    }

    /**
     * Gets the longitude for the given point.
     *
     * @param location
     * @return
     */
    public static double getLongitude(Point location) {
        return location.getLongitude() / COORD_FACTOR;
    }

    /**
     * Gets the deafult features file from classpath.
     *
     * @return
     */
    public static URL getDefaultFeaturesFile() {
        return RouteGuideServer.class.getResource("/Users/berryjam/Documents/workspace/ProtobufUsage/src/main/java/io/grpc/examples/routeguide/route_guide_db.json");
    }

    public static List<Feature> parseFeatures(URL file) throws IOException {
        InputStream input = file.openStream();
        try {
            JsonReader reader = Json.createReader(input);
            List<Feature> features = new ArrayList<Feature>();
            for (JsonValue value : reader.readArray()) {
                JsonObject obj = (JsonObject) value;
                String name = obj.getString("name", "");
                JsonObject location = obj.getJsonObject("location");
                int lat = location.getInt("latitude");
                int lon = location.getInt("longitude");
                Feature feature = Feature.newBuilder().setName(name).setLocation(Point.newBuilder().setLatitude(lat)
                        .setLongitude(lon).build()).build();
                features.add(feature);
            }

            return features;
        } finally {
            input.close();
        }
    }

    /**
     * Indicates whether the given feature exists (i.e. has a valid name).
     *
     * @param feature
     * @return
     */
    public static boolean exists(Feature feature) {
        return feature != null && !feature.getName().isEmpty();
    }

}

package com.linkedin.openhouse.cluster.storage.configs;

import com.linkedin.openhouse.cluster.configs.YamlPropertySourceFactory;
import java.util.HashMap;
import java.util.Map;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This class represents the storage properties for the cluster. It includes the default storage
 * type and a map of different storage types. Each storage type has its own properties such as
 * scheme, root path, endpoint, and parameters. For list of supported storage types, see {@link
 * com.linkedin.openhouse.cluster.storage.StorageType}.
 */
@Configuration
@ConfigurationProperties(prefix = "cluster.storages")
@PropertySource(
    name = "clusterStorage",
    value = "file:${OPENHOUSE_CLUSTER_CONFIG_PATH:/var/config/cluster.yaml}",
    factory = YamlPropertySourceFactory.class,
    ignoreResourceNotFound = true)
@Getter
@Setter
public class StorageProperties {
  private String defaultType;
  private Map<String, StorageTypeProperties> types;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder(toBuilder = true)
  public static class StorageTypeProperties {
    private String scheme;
    private String rootPath;
    private String endpoint;
    @Builder.Default private Map<String, String> parameters = new HashMap<>();
  }
}

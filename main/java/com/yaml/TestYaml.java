package com.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TestYaml {
    public void readYamlWithArray() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("number.yaml");
        Yaml yaml = new Yaml();
        Map<String,String> data = yaml.load(inputStream);
        System.out.println(data);
    }

    public static void main(String[] args) {
       new TestYaml().readYamlWithArray();
    }

}

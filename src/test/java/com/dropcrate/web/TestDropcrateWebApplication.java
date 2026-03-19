package com.dropcrate.web;

import org.springframework.boot.SpringApplication;

public class TestDropcrateWebApplication {

    static void main(String[] args) {
        SpringApplication
                .from(DropcrateWebApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}

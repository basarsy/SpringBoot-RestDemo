package com.basar.restdemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Test {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Inside the before all method.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Inside the after all method.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("\nInside the before each method.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Inside the after each method.");
    }

    @org.junit.jupiter.api.Test
    public void testMethod1(){
        System.out.println("This is my first test!");
    }

    @org.junit.jupiter.api.Test
    public void testMethod2(){
        System.out.println("This is my second test!");
    }
}

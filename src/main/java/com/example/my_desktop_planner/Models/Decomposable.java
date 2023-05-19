package com.example.my_desktop_planner.Models;

public interface Decomposable<T> {
    T decompose(T obj);
}
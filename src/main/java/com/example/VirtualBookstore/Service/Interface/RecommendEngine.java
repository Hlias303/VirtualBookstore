package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.Books;

import java.util.List;

public interface RecommendEngine {

    List<Books> getRecommendationsForUser(int userId, int limit);
}

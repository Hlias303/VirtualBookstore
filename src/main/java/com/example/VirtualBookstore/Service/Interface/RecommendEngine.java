package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.Books;

import java.util.List;

/**
 * Service interface for generating personalized book recommendations.
 * Utilizes collaborative filtering and similarity-based algorithms
 * to suggest books likely matching user preferences.
 */
public interface RecommendEngine {

    /**
     * Generates a personalized list of book recommendations for a given user.
     * Results are limited to the specified maximum number of books.
     *
     * @param userId the unique identifier of the user to generate recommendations for
     * @param limit  the maximum number of recommendations to return
     * @return a list of Books recommended for the user, ordered by relevance
     */
    List<Books> getRecommendationsForUser(int userId, int limit);
}

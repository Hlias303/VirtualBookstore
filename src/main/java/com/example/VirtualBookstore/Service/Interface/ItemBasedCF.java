package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.UserBook;

import java.util.List;
import java.util.Map;

/**
 * Service interface for computing item-based collaborative filtering recommendations.
 * Handles building element maps, frequency matrices, diff matrices,
 * calculating similarity scores, generating predictions, and saving results.
 */
public interface ItemBasedCF {

    /**
     * Retrieves all user-book pairs from the database and formats them
     * into a nested map structure (user → book → rating) for further processing.
     *
     * @param userBooks the list of UserBook entities containing user, book, and rating data
     * @return a nested map where each key is a user ID and each value is a map of book IDs to their ratings
     */
    Map<Integer, Map<Integer, Double>> getElementsFromDB(List<UserBook> userBooks);

    /**
     * Builds both the frequency matrix and the difference matrix from the user-book data.
     * These matrices are used in collaborative filtering to compute similarity.
     *
     * @param userBooks the list of UserBook entities to process for matrix construction
     */
    void BuildDiffAndFreqMatrices(List<UserBook> userBooks);

    /**
     * Calculates pairwise similarity scores between user-book pairs
     * using the frequency and difference matrices generated during matrix building.
     *
     */
    void SimilarityScores();

    /**
     * Generates predicted ratings based on pairwise similarity scores computed previously.
     *
     * @return a nested map where each key is a user ID and each value is a map of predicted ratings per book
     */
    Map<Integer, Map<Integer, Double>> Predictions();

    /**
     * Persists the predicted ratings to the database for later retrieval or analysis.
     *
     */
    void SavePredictions();
}

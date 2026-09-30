package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.UserBook;

import java.util.List;
import java.util.Map;

public interface ItemBasedCF {

    Map<Integer, Map<Integer, Double>> getElementsFromDB(List<UserBook> userBooks);

    void BuildDiffAndFreqMatrices(List<UserBook> userBooks);

    void SimilarityScores();

    Map<Integer, Map<Integer, Double>> Predictions();

    void SavePredictions();
}

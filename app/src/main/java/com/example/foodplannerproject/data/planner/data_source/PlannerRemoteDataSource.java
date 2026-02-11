package com.example.foodplannerproject.data.planner.data_source;

import com.example.foodplannerproject.data.planner.model.PlannerMeal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import io.reactivex.rxjava3.core.Completable;


public class PlannerRemoteDataSource {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public Completable insertMeal(PlannerMeal meal) {
        return Completable.create(emitter -> {
            String uid = auth.getCurrentUser().getUid();

            firestore.collection("users")
                    .document(uid)
                    .collection("planner")
                    .document(meal.getKey())
                    .set(meal)
                    .addOnSuccessListener(unused -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }
}

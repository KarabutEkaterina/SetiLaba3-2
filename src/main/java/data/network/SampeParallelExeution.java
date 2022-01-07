package data.network;

import io.reactivex.Single;
import one.util.streamex.StreamEx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SampeParallelExeution {

    public static Single<String> kok() {
        return getUserSelectedPlace().flatMap(locationName -> {
           Single<String> weatherOperation = getWeather(locationName);

           Single<String> interestingPlacesOperation = getInterestingPlaces(locationName).flatMap(interestingPlaces -> {
               List<Single<String>> descriptionOperations = interestingPlaces.stream()
                       .map(SampeParallelExeution::getDescriptionForPlace)
                       .collect(Collectors.toList());

               return Single.zip(descriptionOperations, descriptionsRaw -> {
                   List<String> descriptions = Arrays.stream(descriptionsRaw)
                           .map(raw -> (String) raw)
                           .collect(Collectors.toList());

                   return StreamEx.zip(interestingPlaces, descriptions, (place, description) -> place + ": " + description)
                           .collect(Collectors.joining("\n"));
               });
           });

           return Single.zip(weatherOperation, interestingPlacesOperation, (weather, interestingPlaces) -> {
               return "Information for " + locationName + ":\n" +
                       "Weather: " + weather + "\n" +
                       "Interesting places:\n" + interestingPlaces;
           });
        });
    }

    static Single<String> getDescriptionForPlace(String interestingPlace) {
        return Single.just("Description of " + interestingPlace);
    }

    static Single<List<String>> getInterestingPlaces(String place) {
        return Single.just(
                Arrays.asList("Place 1", "Place 2", "Place 3", "Place 4", "Place 5")
        );
    }

    static Single<String> getWeather(String place) {
        return Single.just("Yasno");
    }

    static Single<String> getUserSelectedPlace() {
        return Single.just("Berlin");
    }
}

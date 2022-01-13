package data;

import data.network.geocoding.Hit;
import data.network.places.Place;
import data.network.weather.Weather;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LocationsApp {

    private final LocationsRepository repository;

    public LocationsApp(LocationsRepository repository) {
        this.repository = repository;
    }

    public void exec() {
        System.out.println("Enter place to get info for: ");
        Scanner scanner = new Scanner(System.in);
        String place = scanner.next();
        List<Hit> response = repository.findPlaces(place);
        response.forEach(elem -> System.out.println(elem.name + "(" + elem.osmId + ")"));
        System.out.println("Enter the id of place for details: ");
        int osmId = scanner.nextInt();

        Hit selectedPlace = response.stream()
                .filter(item -> item.osmId == osmId)
                .findFirst()
                .get();

        CompletableFuture<String> weather = CompletableFuture
                .supplyAsync(() -> {
                    Single<Weather> currentWeather = repository.getWeather(selectedPlace.point.lat, selectedPlace.point.lng);
                    return "Weather in " + selectedPlace.name + " = " + currentWeather.blockingGet().temp;
                });

        CompletableFuture<Void> info = CompletableFuture
                .supplyAsync(
                        () -> repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng)
                )
                .thenApply(
                        places -> {
                            List<Place> list = places.blockingGet();
                            list.stream().forEach(
                                    item -> {
                                        String description = repository.getPlaceInfo(Integer.parseInt(item.id)).blockingGet().getInfo();
                                        System.out.println("Description for place: " + item.getName() + "\n" + description + "\n");
                                        try {
                                            Thread.sleep(150); // because api provides only 10 requests per second
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                            );
                            return null;
                        }
                );
        try {
            System.out.println(weather.get());
            System.out.println(info.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //  1
    // Single<Integer>.map(number -> number.toString()) -- Single<String>
    // Single<T>, map: (T) -> T1
    // Single<T>, flatMap: (T) -> Single<T>
    public Completable execute() {
//        return input.readLine("Enter place to get info for: ")
//                .flatMap(repository::findPlaces)
//                .flatMap(places -> {
//                    String placesAsString = places.stream()
//                            .map(place -> place.name + "(" + place.osmId + ")")
//                            .collect(Collectors.joining("\n", "Found places:\n", "\nEnter the id of place for details: "));
//
//                    return input.readLine(placesAsString)
//                            .flatMap(idStr -> {
//                                int osmId = Integer.parseInt(idStr);
//
//                                Hit selectedPlace = places.stream()
//                                        .filter(place -> place.osmId == osmId)
//                                        .findFirst()
//                                        .get();
//
////                                return repository.getWeather(selectedPlace.point.lat, selectedPlace.point.lng).map(weather -> {
////                                    System.out.println("Temperature at place: " + weather.temp);
////
////                                    return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
////                                        String interestingPlacesAsString = interestingPlaces.stream()
////                                                .map(place -> place.name + "(" + place.id + ")")
////                                                .collect(Collectors.joining("\n", "Found places:\n", "\n "));
////
////                                       // System.out.println("i am in this function");
////                                        return input.readLine(placesAsString);
////
////                                    });
////
////                                });
//
//                                return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
//                                    String interestingPlacesAsString = interestingPlaces.stream()
//                                            .map(place -> place.name + "(" + place.id + ")")
//                                            .collect(Collectors.joining("\n", "Found places:\n", "\n "));
//
//                                    // System.out.println("i am in this function");
//                                    return input.readLine(placesAsString);
//
//                                });
//
////                                return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
////                                                    String interestingPlacesAsString = interestingPlaces.stream()
////                                                            .map(place -> place.name + "(" + place.id + ")")
////                                                            .collect(Collectors.joining("\n", "Found places:\n", "\n "));
////
////                                        });
//
//
//                                // TODO
//                                /*
//                                С помощью метода [3] ищутся интересные места в локации, далее для каждого найденного места с помощью метода [4] ищутся описания, всё это показывается пользователю в виде списка.
//                                 */
//
//                                // choose place -> weather
//                                //              | -> get interesting places (list) -> get description for place 1 | -> show to user
//                                //                                                 -> get description for place n |
//
//                                        });
//                })
//                .toCompletable()
//                .observeOn(Schedulers.io());
        return null;
    }
}

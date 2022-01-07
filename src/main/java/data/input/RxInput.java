package data.input;

import io.reactivex.Single;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RxInput {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Single<String> readLine(String hint) {
        return Single.fromCallable(() -> {
            System.out.println(hint);

            return reader.readLine();
        });
    }
}

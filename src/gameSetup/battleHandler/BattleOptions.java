package gameSetup.battleHandler;

import units.droids.Droid;

import java.util.Comparator;
import java.util.List;

public class BattleOptions {
    public Droid findWeakestDroid(List<Droid> droids) {
        droids.sort(Comparator.comparing(Droid::getDroidHP));
        return droids.getFirst();
    }

    public Droid findStrongestDroid(List<Droid> droids) {
        droids.sort(Comparator.comparing(Droid::getDroidHP));
        return droids.getLast();
    }

    public Droid findMostEndangeredDroid(List<Droid> droids) {
        droids.sort(Comparator.comparing(Droid::getDroidHP).thenComparing(Droid::getDroidSHD));
        return droids.getFirst();
    }

    public Droid findMostAggressiveDroid(List<Droid> droids) {
        droids.sort(Comparator.comparing(Droid::getDroidDMG));
        return droids.getLast();
    }
}

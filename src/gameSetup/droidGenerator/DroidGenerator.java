package gameSetup.droidGenerator;

import java.util.Random;

import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.*;
import units.droids.*;

public class DroidGenerator {
    private static final Random rand = new Random();

    private static final String[] COMBAT_NAMES = {"CT-47", "RX-99", "KR-10", "TF-21", "XT-88"};
    private static final String[] DEFENDER_NAMES = {"DL-02", "DR-77", "GH-33", "TH-04", "ST-15"};
    private static final String[] SUPPORT_NAMES = {"HS-03", "RL-22", "XR-50", "SD-17", "MR-09"};

    public DroidType convertTextToType(String typeChoice) {
        return switch (typeChoice) {
            case "a" -> DroidType.COMBAT;
            case "b" -> DroidType.DEFENDER;
            case "c" -> DroidType.SUPPORT;
            default -> null;
        };
    }

    public DroidSubtype convertTextToSubtype(DroidType droidType, String subtypeChoice) {
        return switch (droidType) {
            case COMBAT -> switch (subtypeChoice) {
                case "a" -> CombatDroidSubtype.ASSAULT;
                case "b" -> CombatDroidSubtype.SUICIDE_BOMBER;
                default -> throw new IllegalArgumentException("Invalid subtype choice for COMBAT droid type.");
            };
            case DEFENDER -> switch (subtypeChoice) {
                case "a" -> DefenderDroidSubtype.ARMORED;
                case "b" -> DefenderDroidSubtype.GUARDIAN;
                default -> throw new IllegalArgumentException("Invalid subtype choice for DEFENDER droid type.");
            };
            case SUPPORT -> switch (subtypeChoice) {
                case "a" -> SupportDroidSubtype.MEDIC;
                case "b" -> SupportDroidSubtype.TACTICIAN;
                case "c" -> SupportDroidSubtype.ENHANCER;
                default -> throw new IllegalArgumentException("Invalid subtype choice for SUPPORT droid type.");
            };
        };
    }

    public Droid createCombatDroid(CombatDroidSubtype droidSubtype) {
        String droidName = COMBAT_NAMES[rand.nextInt(COMBAT_NAMES.length)];
        return new CombatDroid(droidName, DroidType.COMBAT, droidSubtype);
    }

    public Droid createDefenderDroid(DefenderDroidSubtype droidSubtype) {
        String droidName = DEFENDER_NAMES[rand.nextInt(DEFENDER_NAMES.length)];
        return new DefenderDroid(droidName, DroidType.DEFENDER, droidSubtype);
    }

    public Droid createSupportDroid(SupportDroidSubtype droidSubtype) {
        String droidName = SUPPORT_NAMES[rand.nextInt(SUPPORT_NAMES.length)];
        return new SupportDroid(droidName, DroidType.SUPPORT, droidSubtype);
    }

    public Droid produceDroid(String droidTypeChoice, String droidSubtypeChoice) {
        DroidType droidType = convertTextToType(droidTypeChoice);
        DroidSubtype droidSubtype = convertTextToSubtype(droidType, droidSubtypeChoice);
        
        return switch (droidType) {
            case COMBAT -> createCombatDroid((CombatDroidSubtype) droidSubtype);
            case DEFENDER -> createDefenderDroid((DefenderDroidSubtype) droidSubtype);
            case SUPPORT -> createSupportDroid((SupportDroidSubtype) droidSubtype);
        };
    }
}
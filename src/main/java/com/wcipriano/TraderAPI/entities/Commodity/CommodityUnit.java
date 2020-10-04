package com.wcipriano.TraderAPI.entities.Commodity;

import com.wcipriano.TraderAPI.exceptions.CommodityUnitPluralNameNotFoundException;

public enum CommodityUnit {
    BUSHEL,
    TON,
    TONNE,
    SHORT_TON,
    CENTUM,
    POUND,
    KILOGRAM,
    GALLON,
    THERM,
    BARREL;

    public String getPlural() throws CommodityUnitPluralNameNotFoundException {
        switch (this) {
            case BUSHEL:
                return "bushels";

            case TON:
                return "tons";

            case SHORT_TON:
                return "short tons";

            case TONNE:
                return "tonnes";

            case CENTUM:
                return "centums";

            case POUND:
                return "pounds";

            case KILOGRAM:
                return "kilograms";

            case GALLON:
                return "gallons";

            case THERM:
                return "therms";

            case BARREL:
                return "barrels";
        }
        throw new CommodityUnitPluralNameNotFoundException("The commodity unit requested has no plural unit defined.");
    }

}

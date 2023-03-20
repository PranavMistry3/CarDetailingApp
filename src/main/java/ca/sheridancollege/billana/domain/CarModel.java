package ca.sheridancollege.billana.domain;

public enum CarModel {
    CAMRY(CarMake.TOYOTA),
    COROLLA(CarMake.TOYOTA),
    ACCORD(CarMake.HONDA),
    CIVIC(CarMake.HONDA),
    FOCUS(CarMake.FORD),
    MUSTANG(CarMake.FORD),
    CRUZE(CarMake.CHEVROLET),
    MALIBU(CarMake.CHEVROLET),
    ALTIMA(CarMake.NISSAN),
    SENTRA(CarMake.NISSAN),
    X5(CarMake.BMW),
    THREE_SERIES(CarMake.BMW),
    E_CLASS(CarMake.MERCEDES_BENZ),
    C_CLASS(CarMake.MERCEDES_BENZ),
    A4(CarMake.AUDI),
    A6(CarMake.AUDI),
    JETTA(CarMake.VOLKSWAGEN),
    PASSAT(CarMake.VOLKSWAGEN),
    SONATA(CarMake.HYUNDAI),
    ELANTRA(CarMake.HYUNDAI);
    
    private final CarMake make;

    CarModel(CarMake make) {
        this.make = make;
    }
    
    public CarMake getMake() {
        return make;
    }
}


package ca.sheridancollege.billana.domain;

public enum CarDetailingService {

	INTERIOR_DETAILING("Interior Detailing"),
    EXTERIOR_DETAILING("Exterior Detailing"),
    FULL_DETAILING("Full Detailing"),
    PAINT_CORRECTION("Paint Correction"),
    CERAMIC_COATING("Ceramic Coating");

    private String name;

    private CarDetailingService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

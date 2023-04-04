package ca.sheridancollege.billana.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @Column(nullable = false)
    private String carMake;

    @Column(nullable = false)
    private String carModel;
    
    @Column(nullable = false)
    private String yearMade;

    @OneToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vechicle;
	
	@OneToOne
	@JoinColumn(name = "detailer_id")
	private Detailer detailer;
    
    @Column(nullable = false)
    private String serviceType;

    @Lob
    private String additionalDetails;
}


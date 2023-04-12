package ca.sheridancollege.billana.domain;

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
    
//    @Column(nullable = false)
//    private String carMake;
//
//    @Column(nullable = false)
//    private String carModel;
//    
//    @Column(nullable = false)
//    private String yearMade;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(nullable = false)
    private String serviceType;

    @Lob
    private String additionalDetails;
    
//    @Override
//    public String toString() {
//        return "Appointment{" +
//                "id=" + id +
//                ", appointmentDateTime=" + appointmentDateTime +
//                ", customer=" + customer +
//                ", vehicle=" + vehicle +
//                ", serviceType='" + serviceType + '\'' +
//                ", additionalDetails='" + additionalDetails + '\'' +
//                '}';
//    }
    
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDateTime=" + appointmentDateTime +
                ", serviceType='" + serviceType + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                '}';
    }

}


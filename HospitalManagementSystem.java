import java.util.ArrayList;
import java.util.Scanner;

// Class to store patient information
class Patient {
    int id;
    String name;
    int age;
    String gender;
    String contact;
    String address;

    public Patient(int id, String name, int age, String gender, String contact, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }
}

// Class to store doctor information
class Doctor {
    int id;
    String name;
    String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}

// Class to store appointment information
class Appointment {
    int id;
    int patientId;
    int doctorId;
    String appointmentDate;
    String appointmentTime;

    public Appointment(int id, int patientId, int doctorId, String appointmentDate, String appointmentTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }
}

// Class to store EHR (Electronic Health Record)
class EHR {
    int patientId;
    int doctorId;
    String diagnosis;
    String prescription;
    String notes;

    public EHR(int patientId, int doctorId, String diagnosis, String prescription, String notes) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
    }
}

public class HospitalManagementSystem {
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<EHR> ehrs = new ArrayList<>();
    static int patientIdCounter = 1;
    static int doctorIdCounter = 1;
    static int appointmentIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Adding sample doctors
        doctors.add(new Doctor(doctorIdCounter++, "Dr. Smith", "Cardiologist"));
        doctors.add(new Doctor(doctorIdCounter++, "Dr. Adams", "Neurologist"));
        doctors.add(new Doctor(doctorIdCounter++, "Dr. Johnson", "Orthopedic"));

        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View EHR");
            System.out.println("4. View Record Counts"); // New option to view record counts
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    viewEHR();
                    break;
                case 4:
                    viewRecordCounts(); // Function to display counts of records
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void registerPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient name:");
        String name = scanner.nextLine();
        System.out.println("Enter patient age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter patient gender (Male/Female):");
        String gender = scanner.nextLine();
        System.out.println("Enter patient contact:");
        String contact = scanner.nextLine();
        System.out.println("Enter patient address:");
        String address = scanner.nextLine();

        patients.add(new Patient(patientIdCounter++, name, age, gender, contact, address));

        System.out.println("Patient registered successfully!");
    }

    public static void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient ID:");
        int patientId = scanner.nextInt();
        System.out.println("Enter doctor ID:");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        System.out.println("Enter appointment time (HH:MM):");
        String time = scanner.nextLine();

        appointments.add(new Appointment(appointmentIdCounter++, patientId, doctorId, date, time));

        System.out.println("Appointment scheduled successfully!");
    }

    public static void viewEHR() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient ID:");
        int patientId = scanner.nextInt();

        System.out.println("\n--- Electronic Health Record ---");
        for (EHR ehr : ehrs) {
            if (ehr.patientId == patientId) {
                System.out.println("Doctor ID: " + ehr.doctorId);
                System.out.println("Diagnosis: " + ehr.diagnosis);
                System.out.println("Prescription: " + ehr.prescription);
                System.out.println("Notes: " + ehr.notes);
                System.out.println("----------------------------------");
            }
        }
    }

    public static void addEHR(int patientId, int doctorId, String diagnosis, String prescription, String notes) {
        ehrs.add(new EHR(patientId, doctorId, diagnosis, prescription, notes));
    }

    // New function to view record counts
    public static void viewRecordCounts() {
        System.out.println("\n--- Record Counts ---");
        System.out.println("Total Patients: " + patients.size());
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Appointments: " + appointments.size());
        System.out.println("Total EHRs: " + ehrs.size());
    }
}

# CareerNest — Collections and File Handling
Campus placement terminal prototype. Java 17 or newer; no external libraries or database server required.

## Run from project root (PowerShell)
```powershell
mkdir out
javac -d out src/main/java/*.java
java -cp out Main
```
Alternatively: `mvn clean compile exec:java`.

Records are stored in `data/` as pipe-separated text. Do not enter pipe characters in names or titles. Data is saved after each change. Run from the same project folder each time. Old database records are not migrated. Single-user prototype; do not run multiple instances against the same data. Back up the data folder before editing it manually.

## Scope
14 menu options: students, companies, jobs, eligibility, applications, interviews and search. Login/profile helper classes are retained but not exposed in the menu. No role-based access control. Remaining course topics such as threading and sorting can be added if required.

## Member distribution
1. Person, Student, StudentProfile, Education, Skill, Certification, StudentManager, ProfileManager, RegistrationService, StudentRepository, InputHandler, InputValidator, StorageInitializer, ErrorHandler.
2. Company, Recruiter, Job, JobCategory, CompanyManager, RecruiterManager, JobManager, RecruitmentDrive, JobSearchService, CompanyRepository, JobRepository, StorageConfig, FileStorage, StorageException.
3. Application, ApplicationStatus, EligibilityResult, DuplicateApplicationException, IneligibleStudentException, CgpaEligibilityRule, BranchEligibilityRule, BacklogEligibilityRule, ApplicationValidator, ApplicationHistoryService, EligibilityService, EligibilityRuleEngine, ApplicationService, ApplicationRepository, EligibilityRule (interface).
4. Interview, InterviewManager, InterviewRepository, User, UserRepository, UserValidator, LoginService, SessionManager, Main, MainMenu, MenuRenderer, ConsoleTableFormatter, ConsoleNavigator, RecordValidator.

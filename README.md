# CareerNest — 56 Simple Java Classes

Terminal-based campus placement system for a second-year Java OOP coursework demonstration. Uses Java 17, MySQL and JDBC. All 56 class names match the four-member plan. Classes are in the default package for ease of learning.

## Run
1. Install JDK 17+, Maven and MySQL Server.
2. Run `database/careernest.sql` in MySQL Workbench.
3. In the project directory, set database settings in PowerShell:
   ```powershell
   $env:DB_USER="root"
   $env:DB_PASSWORD="your_mysql_password"
   ```
   Optionally set `DB_URL` (default: `jdbc:mysql://localhost:3306/careernest`).
4. Run `mvn clean compile exec:java`.

## Typical demo
Register student -> Add company -> Add job -> Check eligibility -> Apply -> View history -> Shortlist -> Schedule interview.

## Member split
- Member 1 (14): Person, Student, User, StudentProfile, Education, Skill, Certification, StudentManager, UserValidator, ProfileManager, RegistrationService, LoginService, StudentDAO, UserDAO.
- Member 2 (14): Company, Recruiter, Job, Interview, JobCategory, CompanyManager, RecruiterManager, JobManager, InterviewManager, RecruitmentDrive, JobSearchService, CompanyDAO, JobDAO, InterviewDAO.
- Member 3 (14): Application, ApplicationStatus, EligibilityResult, DuplicateApplicationException, IneligibleStudentException, CgpaEligibilityRule, BranchEligibilityRule, BacklogEligibilityRule, ApplicationValidator, ApplicationHistoryService, EligibilityService, EligibilityRuleEngine, ApplicationService, ApplicationDAO.
- Member 4 (14): Main, MainMenu, MenuRenderer, ConsoleTableFormatter, DatabaseException, InputHandler, InputValidator, DatabaseConfig, SessionManager, ErrorHandler, ConsoleNavigator, DBConnection, DatabaseInitializer, TransactionManager.

## Notes
- This is a classroom demonstration, not a production system: there is no secure login/role authorization, and some supporting classes are simple building blocks rather than separate menu options.
- IDs are entered manually for students, companies and jobs; application/interview IDs are auto-generated.
- The database enforces duplicate-application prevention too.
- SQL connection credentials should not be committed in source code.

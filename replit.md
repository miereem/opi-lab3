# TPO Lab 1 - Apache Ant Build Automation

## Project Overview
This is a university laboratory project for Software Testing and Quality Assurance (TPO) course. The project implements a comprehensive Apache Ant build automation system with 16 different build targets for a Java application.

## Purpose
Create an advanced Ant build script that automates:
- Compilation and packaging
- JUnit testing
- Git version control operations
- Code documentation and validation
- Deployment and distribution
- Build environment management

## Project Structure
```
tpo-lab1/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── Main.java
│   │   │   ├── task1/Cos.java
│   │   │   ├── task2/ShellSort.java
│   │   │   └── task3/ (Dolphin, Human, Race, Thing)
│   │   └── resources/
│   │       ├── messages_en.properties
│   │       └── messages_ru.properties
│   └── test/java/ (JUnit 5 tests)
├── build.xml (Main Ant build script)
├── build.properties (Configuration variables)
├── MANIFEST.MF (JAR manifest template)
└── lib/ (Dependencies: JUnit, Lombok)
```

## Build Targets

### Core Targets
- **compile** - Compiles Java source code and tests
- **build** - Creates executable JAR file
- **clean** - Removes all build artifacts
- **test** - Runs JUnit tests

### Advanced Targets
- **xml** - Validates all XML files in project
- **doc** - Generates Javadoc and adds MD5/SHA-1 checksums to MANIFEST
- **native2ascii** - Converts localization files
- **env** - Runs application in alternative JVM environment
- **alt** - Creates alternative version with renamed classes/variables

### Git Integration
- **report** - Saves test reports and commits to Git
- **team** - Builds 4 previous Git revisions and packages as ZIP
- **diff** - Checks Git status and commits if watched classes changed
- **history** - Rolls back to last working version if compilation fails

### Deployment
- **scp** - Deploys JAR to remote server via SCP
- **music** - Plays sound on successful build

## Dependencies
- Java 19 (GraalVM)
- Apache Ant 1.10.15
- JUnit 5.8.2
- Lombok 1.18.32

## Usage

### Basic Build
```bash
ant build
```

### Run Tests
```bash
ant test
```

### Full Build with Documentation
```bash
ant doc
```

### Create Alternative Version
```bash
ant alt
```

### Deploy to Server
```bash
ant scp
```

### List All Targets
```bash
ant help
```

## Configuration
All build parameters are externalized in `build.properties`:
- Directory paths
- JAR naming
- Git configuration (including watched classes)
- SCP deployment settings
- Alternative build settings
- Dependency versions (JUnit, Lombok)

### Git Watched Classes
The `diff` target monitors specific classes for changes using the `git.classes.to.watch` property:
```properties
git.classes.to.watch=org.example.Main,org.example.task1.Cos
```
This property expects fully qualified class names (comma-separated). The build system extracts the simple class name (e.g., "Main" from "org.example.Main") and checks if the corresponding .java file appears in `git diff --name-only`. Only when watched files are modified will the diff target commit changes.

## Recent Changes
- 2025-11-14: Initial setup of complete Ant build system
  - Created comprehensive build.xml with all 16 required targets
  - Configured automatic JUnit dependency download
  - Set up localization file structure
  - Integrated Git operations
  - Added Lombok support for annotations

## Architecture Decisions
1. **Dependency Management**: Using Ant's `<get>` task to download JUnit libraries automatically
2. **Failsafe Compilation**: Test compilation is non-blocking (failonerror="false")
3. **Property Externalization**: All constants moved to build.properties for easy customization
4. **Modular Targets**: Each target has clear dependencies and single responsibility
5. **Git Integration**: Direct git command execution for version control operations

## Notes
- Main class: `org.example.Main`
- Default target: `build`
- JUnit tests use Jupiter (JUnit 5)
- Lombok annotations require lombok.jar in classpath
- SCP deployment requires SSH key configuration

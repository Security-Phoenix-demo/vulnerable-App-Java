# vulnerable-App-Java

> **DELIBERATELY VULNERABLE. Do not deploy, do not copy code from here.**

A small test fixture for Phoenix Security remediation testing (the "remedy-fix" programme). It
gives the remediation pipeline one repository with every manifest type it needs to exercise:

| File | Purpose | Known-vulnerable dependencies |
|---|---|---|
| `pom.xml` (root) | Maven SCA findings; the Maven version-bump producer | `log4j-core` 2.14.1 (CVE-2021-44228), `commons-text` 1.9 (CVE-2022-42889), `jackson-databind` 2.9.8 |
| `build.gradle` + `gradlew` | Mirrors `pom.xml` so Phoenix's Gradle validation can run | same as `pom.xml` |
| `package.json` + `package-lock.json` | npm SCA findings; lockfile-aware npm producer | `lodash` 4.17.15 (CVE-2020-8203), `minimist` 1.2.0 (CVE-2020-7598), `axios` 0.21.0 (CVE-2020-28168) |
| `requirements.txt` | pip validation | `requests` 2.19.1, `PyYAML` 5.3.1 (CVE-2020-14343), `urllib3` 1.24.1 |
| `src/main/java/.../App.java` | SAST findings | MD5 hashing, SQL string concatenation, user input into log4j and commons-text interpolation |

`requirements.txt` holds `name==version` lines only, so the pip validation path accepts it.

## Build

```bash
./gradlew test
```

The Maven build (`mvn test`) uses the same dependencies.

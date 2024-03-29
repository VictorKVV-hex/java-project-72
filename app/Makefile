run-dist:
	./build/install/app/bin/app src/test/fixtures/fileNested1.json src/test/fixtures/fileNested2.json

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build

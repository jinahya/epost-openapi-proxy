#!/bin/sh
#serviceKey=QO8Jo5UWN4rASy9NIoiUXwQBSbOmphw6UT41sDCNzo8bBQDmJuseGioU3KK94sgHrXmliI5SGphq1akyyWJ5Qg==
serviceKey=IfJN7A3cBBPttYf/FcFWC8pNDT3mi3SRSsDJmyAXQAUOlqvkQhP4ggZkHzhacIhEEJzcswWo8fraVeUBAOxQng==
SPRING_PROFILES_ACTIVE=development ./gradlew -DserviceKey=$serviceKey clean bootTestRun
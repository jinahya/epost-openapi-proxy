#!/bin/sh
dirname="$(dirname "$0")"
. "$dirname/_service_key.sh"
#SPRING_PROFILES_ACTIVE=development ./gradlew -DserviceKey=$serviceKey clean integrationTest --warning-mode all
SPRING_PROFILES_ACTIVE=development ./gradlew -DserviceKey=$serviceKey clean check --warning-mode all

#!/bin/sh
dirname="$(dirname "$0")"
. "$dirname/__service_key.sh"
SPRING_PROFILES_ACTIVE=development ./gradlew -DserviceKey=$serviceKey clean check --warning-mode all

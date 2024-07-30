#!/bin/sh
### https://unix.stackexchange.com/a/780769/8557
grep -R serialVersionUID | sort -k2 -b |
awk '
    { currKey=$0; sub(/^[^[:space:]]+[[:space:]]+/,"",currKey) }
    currKey == prevKey { print firstLine $0; firstLine=""; next }
    { prevKey=currKey; firstLine=$0 ORS }
'

BEGIN {
    #include <stdio.h>
    r = 0;
    d = 0;
} {
    if ($1 == "d")
        d++;
} {
    if ($1 == "r")
        r++;
}
END {
    printf("\n Packets received = %d \n", r);
    printf("\n Packets dropped = %d \n", d);
}
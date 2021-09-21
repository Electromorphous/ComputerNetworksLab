BEGIN {
    #include <stdio.h>
    d = 0;
    r = 0;
}
{
    if ($1 == "r")
        r++;
}
{
    if ($1 == "d")
        d++;
}
END {
    printf("Number of packets received = %d \n", r);
    printf("Number of packets dropped = %d \n", d);
}
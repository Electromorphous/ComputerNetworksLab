BEGIN {
    #include <stdio.h>
    d = 0;
    r = 0;
}
{
    if ($1 == "d")
        d++;
}
{
    if ($1 == "r")
        r++;
}
END {
    printf("Total received = %d \n", r);
    printf("Total dropped = %d \n", d);
}

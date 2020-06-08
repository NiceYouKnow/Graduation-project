#include "aes.h"
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    char *c = "1111111111111111";
    char *key = "abc1111111111111";
    aes(c,strlen(c),key);
    return 0;
}

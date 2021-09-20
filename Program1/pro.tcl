set ns [new Simulator]
set nf [open nam.nam w]
$ns namtrace-all $nf
set tf [open trace.tr w]
$ns trace-all $tf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n2 800Kb 100ms DropTail
$ns duplex-link $n1 $n2 5Mb 200ms DropTail

$ns set queue-limit $n0 $n2 50
$ns set queue-limit $n1 $n2 10

set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null [new Agent/Null]
$ns attach-agent $n2 $null

$ns connect $udp0 $null
$ns connect $udp1 $null

$ns color 1 "red";
$ns color 2 "blue";

$udp0 set class_ 1;
$udp1 set class_ 2;

$n0 label "Source 1"
$n1 label "Source 2"
$n2 label "Destination"

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam nam.nam
    exit 0
}

$ns at 0.001 "$cbr0 start"
$ns at 0.001 "$cbr1 start"
$ns at 5.0 "finish"
$ns run

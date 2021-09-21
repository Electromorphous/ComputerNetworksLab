set ns [new Simulator]
set tf [open trace.tr w]
$ns trace-all $tf
set nf [open nam.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n2 10Kb 100ms DropTail
$ns duplex-link $n1 $n2 5Mb 200ms DropTail

# maximum limit for DropTail is 10 (not sure refer the docs don't trust me I am not a reliable source)
$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n1 $n2 5

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

$ns color 1 "red"
$ns color 2 "blue"

$udp0 set class_ 1
$udp1 set class_ 2

$n0 label "Source 1"
$n1 label "Source 2"
$n2 label "Destination"

# $cbr0 set packetSize_ 10Kb
# $cbr0 set interval_ 0.01

$cbr1 set packetSize_ 500Mb
$cbr1 set interval_ 0.001

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

set ns [new Simulator]
set tf [open trace.tr w]
$ns trace-all $tf
set nf [open nam.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
$n0 color "green"
$n0 label "src1"

set n1 [$ns node]
$n1 color "magenta"

set n2 [$ns node]
$n2 color "green"
$n2 label "src2"

set n3 [$ns node]
$n3 color "blue"
$n3 label "dest2"

set n4 [$ns node]
$n4 shape box

set n5 [$ns node]
$n5 color "blue"
$n5 label "dest1"

$ns make-lan "$n0 $n1 $n2 $n3 $n4 $n5" 50Mb 100ms LL Queue/DropTail Mac/802_3

$ns duplex-link $n4 $n5 1Mb 1ms DropTail
$ns duplex-link-op $n4 $n5 orient right

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ftp0 set packetSize_ 500
$ftp0 set interval_ 0.0001

set sink0 [new Agent/TCPSink]
$ns attach-agent $n5 $sink0

$ns connect $tcp0 $sink0

set tcp1 [new Agent/TCP]
$ns attach-agent $n5 $tcp1

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ftp1 set packetSize_ 600
$ftp1 set interval_ 0.001

set sink1 [new Agent/TCPSink]
$ns attach-agent $n3 $sink1

$ns connect $tcp1 $sink1

set file0 [open file0.tr w]
$tcp0 attach $file0
set file1 [open file1.tr w]
$tcp1 attach $file1

$tcp0 trace cwnd_
$tcp1 trace cwnd_

proc finish {} {
    global ns nf tf file0 file1
    $ns flush-trace
    close $nf
    close $tf
    close $file0
    close $file1
    exec nam nam.nam
    exit 0
}

$ns at 0.1 "$ftp0 start"
$ns at 0.2 "$ftp1 start"
$ns at 5.0 "$ftp0 stop"
$ns at 7.0 "$ftp0 start"
$ns at 8.0 "$ftp1 stop"
$ns at 10  "$ftp1 start"
$ns at 14  "$ftp0 stop"
$ns at 15  "$ftp1 stop"

$ns at 16  "finish"
$ns run

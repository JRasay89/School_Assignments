#!/usr/bin/perl
use strict;
use warnings;

my $p_1 = "Foo";
my $p_2 = "bar";
my $p_3 = "foofoobar";
print "PROBLEM 1:\n";

$p_1 =~ s/foo/bar/;
$p_2 =~ s/foo/bar/;
$p_3 =~ s/foo/bar/;

print $p_1,"\n";
print $p_2,"\n";
print $p_3,"\n";

my $text = "my cat is red";
my $text2 = "my dog is red";
my $text3 = "my frog is red";

$text =~ s/my\s(\w{3})\sis\s([rR]ed)/rat\1/;
$text2 =~ s/my\s(\w{3})\sis\s([rR]ed)/rat\1/;
$text3 =~ s/my\s(\w{3})\sis\s([rR]ed)/rat\1/;

print "$text\n";
print "$text2\n";
print "$text3\n";


my $p1 = "8081234567";
my $p2 = "808-123-4567";
my $p3 = "808 123 4567";
my $p4 = "(808) 123 45 67";
my $p5 = "+18081234567";
my $p6 = "+1 808 123 4567";
my $ss = "hello ( ";

if ($p6 =~ /^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/) {
	print "THIS PHONE NUMBER IS VALID!\n";
}

#$phone =~ s/^(\d{3})(\d{3})(\d{4})$/($1) $2-$3/;
#$p2 =~ s/^(\d{3})[\-]?(\d{3})[\-]?(\d{4})$/($1) $2-$3/;
#$p1 =~ s/^(\d{3})[\-]?(\d{3})[\-]?(\d{4})$/($1) $2-$3/;
#$p3 =~ s/^(\d{3})[\s\-]?(\d{3})[\s\-]?(\d{4})$/($1) $2-$3/;
#$p4 =~ s/^[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
#$p5 =~ s/^[+]?\d?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;

#TESTING FINAL REGEX
$p6 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
$p1 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
$p2 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
$p3 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
$p4 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;
$p5 =~ s/^[+]?\d?[\s]?[(]?(\d{3})[)]?[\s\-]?(\d{3})[\s\-]?(\d{2})[\s]?(\d{2})$/($1) $2-$3$4/;

print $p1, "\n";
print $p2, "\n";
print $p3, "\n";
print $p4, "\n";
print $p5, "\n";
print $p6, "\n";





my $rg = "     ";
my $rg2 = "\$hello";
my $rg3 = "foo(3,'h',\$s);";
my $arr = "\$a[3]";
my $param = "(3,hi);";
my $brack = "[]";
my $str = "\"regx string matching is fun\"";
my $str2 = "regx string matching is fun";
my $comp = "\$i>=\$a[2]";
my $assign = "\$myarray[\$i]=\'foo\'";
my $ex = " \"Hello Perl\"";

my $finale = "This is a perl instructions. Which uses \$myarray[\$i]=\'foo\' abd foo(3,'h',\$s) ";





my $spaces = qr/[\t\s]*/;
my $regVar = qr/^(\$|\@){1}[A-Za-z_][A-Za-z0-9_]*$/;
my $regSub = qr/^[A-Za-z_][A-Za-z0-9_]*([(].*[)])?[;]$/;
my $reg_access_array = qr/^(\$){1}[A-Za-z_][A-Za-z0-9_]*([[]((\${1}[A-Za-z]*)|\d*)[]])$/;
my $reg_quoted_strings = qr/((["]{1}[A-Za-z0-9_\s\t]*["]{1})|([']{1}[A-Za-z0-9_\s\t]*[']{1}))/;
my $reg_nonquoted_strings = qr/[A-Za-z0-9_\s\t]*/;
my $reg_comparrisson = qr/^((\${1}[A-Za-z_][A-Za-z0-9_]*([[]((\${1}[A-Za-z]*)|\d*)[]])?)|[0-9]*)?[\s\t]*(([=]{2})|[<]|[>]|([>][=])|([<][=])){1}[\s\t]*((\${1}[A-Za-z_][A-Za-z0-9_]*([[]((\${1}[A-Za-z]*)|\d*)[]])?)|[0-9]*)+$/;
my $reg_assignments = qr/^(\${1}[A-Za-z_][A-Za-z0-9_]*([[]((\${1}[A-Za-z]*)|\d*)[]])?)[\s\t]*[=]{1}[\s\t]*((\${1}[A-Za-z_][A-Za-z0-9_]*([[]((\${1}[A-Za-z]*)|\d*)[]])?)|[0-9]*|((["]{1}[A-Za-z0-9_\s\t]*["]{1})|([']{1}[A-Za-z0-9_\s\t]*[']{1})))$/;


my $regex = qr/$spaces|$regVar|$regSub|$reg_access_array|$reg_quoted_strings|$reg_nonquoted_strings|$reg_comparrisson|$reg_assignments/;

if ($rg =~ $spaces) {
	print "FOUND Spaces!\n";
}
if ($rg2 =~ $regVar) {
	print "Found Variable!\n";
}
if ($rg3 =~ $regSub ) {
	print "Found Subroutine!\n";
}
if ($arr =~ $reg_access_array) {
	print "Found Array!\n";
}

if ($str =~ $reg_quoted_strings) {
	print "Found Qouted Strings\n";
}
if ($str2 =~ $reg_nonquoted_strings) {
	print "Found NonQouted Strings\n";
}

if ($comp =~ $reg_comparrisson) {
	print "Found Comparrison expressions!\n";
}

if ($assign =~ $reg_assignments) {
	print "Found Assignment!\n";
}
if ($finale =~ /$regex/) {
	print "FRAUD\n";
}
#if ($param =~ /([(].*[)])/ ) {
	#print "Found Param!\n";
#}
#if ($param =~ /([(]		((["|']?[A-Za-z0-9]*["|']?)|((["|']?[A-Za-z0-9]*["|']?)(,(["|']?[A-Za-z0-9]*["|']?))+)*)?		[)])/ ) {
	#print "Found Param!\n";
#}
#if ($brack =~ /([[]])/) {
	#print "Found Brackets!\n";
#}




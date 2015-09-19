#!/usr/bin/env perl
use strict;
use warnings;
use LWP::Simple; # allows to retrieve a file given its URL

our $URL = "http://hea.thebus.org/nextbus.asp?s=";

sub main {
	my $stopId = 864; # East-West Rd & Maile Way
	my $path = $URL . $stopId;
	print "Fetching data for bus stop " . $stopId . "\n";
	my $content = get($path);
	die "Error fetching source" unless defined $content;
	print "Fetched " . length($content) . "bytes\n";
	# Uncomment to show fetched data:
	#print $content;
	extract($content);
	return 0;
}

sub extract {
	# extract data into meaningful structure
	my $data = $_[0]; #storing data
	
	# put all list-related start and end tags on separate lines
	$data =~ s/(<\/?li>|<\/?ul>|<\/?ol>)/\n$1\n/g;
	# replace <br> tag with space
	$data =~ s/<br>/ /g;
	# get rid of all tags
	$data =~ s/\<[^\<]+\>//g;
	# get rid of other unwanted characters
	$data =~ s/\&.*;//g;
	
	#splits text into lines and store them into an array
	my @lines = split (/^/, $data); 
	
	#Using grep to only get bus data
	@lines = grep(/.*ound/,@lines);
	#uncomment to show bus data
	#print "@lines";
	filter(@lines);
	
}

sub filter {
	# Work on extracted data
	#For filtering by bus number
	my $bus_number = ".*13";
	#Filtering out by bus ex bus 13
	print "\n\n***Filter Data By Bus.***\n\n";
	if (grep(/$bus_number/,@_)) { #print buses
		print grep(/$bus_number/,@_);
	}
	else { #print if no buses found
		print "NO BUSES HAVE BEEN FOUND!";
	}
	
	#Filter by arriving Buses	
	print "\n\n\n***Filter by arriving Buses.***\n\n";
	if (grep(/arriving/,@_)){	#print buses 
		print grep(/arriving/,@_);
	}
	else { #print if no buses found
		print "NO ARRIVING BUSES HAVE BEEN FOUND";
	}
	
	#Filter by no GPS
	print "\n\n\n***Filter By Buses with no GPS***\n\n";
	if (grep(/no GPS/,@_)) { #print buses
		print grep(/no GPS/,@_);
	}
	else { #print if no buses have been found
		print "No Buses have been found!";
	}
}

exit(main());

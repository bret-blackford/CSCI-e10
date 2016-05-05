
# modified by:	M Bret Blackford
#		ID: 20849347
# CSCI e-10b  Spring 2016
#
## Register usage:
##	$t0	- lower limit, the starting oint
##	$t1	- upper limit, the ending poiny
##	$t5 	- the counter for the divisor (the lower number)
##	$t6	- the remainder value from the HI register
##	$s0	- summation of divisors with no remainder - if equal $t0 is perfect


  
        .text
  
main:
        li $s0, 0			# SUM of integers found 
  
	lw	$t0, START_NUM		# lower limit
	lw	$t1, UPPER_LIMIT	# upper limit

## LOOP FROM START_NUM TO UPPER_LIMIT ###############################################  
outer_loop:
        beq	$t0, $t1, exit		# loop until hit upper limit
        li	$t5, 1			# counter for divisor (initialize divisor)


## FOR EACH NUMBER BETWEEN START_NUM AND UPPER_LIMIT CHECK FOR PERFECT ##############  
inner_loop:
        beq	$t0, $t5, check_for_perfect	# we're done dividing this number
        div	$t0, $t5		# divide s1 by a number less than itself 
        mfhi	$t6			# moves result from Hi register (remainder) to $t5
        beqz	$t6, sum		# if Hi register is 0 result is an integer (no remainder) so add to SUM
        add	$t5, $t5, 1		# otherwise increment divisor
        b	inner_loop		# check if next number is a divisor
  
  
  
sum:
        add	$s0, $s0, $t5		# add divisor to current sum of divisors
        add	$t5, $t5, 1		# increment divisor
        b	inner_loop
  
  
  
print_num:
        li	$s0, 0			# reset sum
        move	$a0, $t0		# print the perfect number
        li	$v0, 1                                     
        syscall
  
        la     $a0, newline		# print newline to pretty up output on screen
        li     $v0, 4
        syscall
  
        add	$t0, $t0, 1		# increment number - test next number for perfect
        b	outer_loop		# branch to beginning of outer loop
  
check_for_perfect: 
        beq	$s0, $t0, print_num	# if SUM of int divsors = number is perfect
  
increment:
        add	$t0, $t0, 1		# increment to next number for IS PERFECT inner_loop check
        li	$s0, 0			# reset sum 
        b 	outer_loop		# branch to beginning of outer loop
  
exit:
	li	$v0, 10			# quit program
        syscall
  
  
 ## Here is where the data for this program is stored:
        .data
  
        newline:	.asciiz  "\n"
	UPPER_LIMIT:	.word	500
	START_NUM:	.word 	5


# M Bret Blackford
# ID: 20849347
# CSCI E-10b  Spring 2016
# last_modified: 04/29/2016
#
# Program prints the SMALLEST and LARGEST values
# found in a non-empty table of N word-sized 
# integers.
#
#	$t0 - address of table
#	$t1 - value of TABLE_SIZE
#	$t2 - 
#	$t3 - contents of $t1 (TABLE_SIZE)
#	$t4 - for_loop counter
#	$s2 - current table value ($t0)
#	$s5 - largest counter
#	$s4 - smallest counter 

########## ##TEXT#### ########## ##########
	.text
	.globl main

main:
	la	$t0, table2	# $t0 = address of table
	#la	$t0, table3	# $t0 = address of table
	la	$t1, TABLE_SIZE	# $t1 = address at end of table
	lw	$t3, ($t1)		# t3 contains the value of address t1
	li	$t4, 1		# set counter <$t4> to 1
	li	$s1,	0		# set counter <$s1> to zero
	lw	$s2, ($t1)

	lw	$s3, ($t0) #$s3 contains the current table value
	lw	$s5, ($t0)	#1st num is largest
	lw	$s4, ($t0)	#1st num is smallest


for_loop:

	bgt	$s3, $s5 biggest_num	# if $s3 > $s5 branch
	blt	$s3, $s4 smallest_num # if $s3 < $s4 branch

return1:

	bge	$t4, $t3, end_for_loop  # if $t4 >= $t3 branch		

	addiu	$t0, $t0, 4	# i++ (word is 4 bytes) 
	addi	$t4, $t4, 1	# add 1 to counter
	lw	$s3, ($t0) 	# load the value of $t0 into $s3 

	b	for_loop		# branch to for_loop

end_for_loop:

	la	$a0, table_msg	#
	lw	$v0, print_string
	syscall
	move	$a0, $t3
	lw	$v0, print_int
	syscall


	la	$a0, biggest_msg		#load label into $a0 for syscall
	lw	$v0, print_string	#load label into $v0 for syscall
	syscall
	move	$a0, $s5			#load contents of $s5 into $a0
	lw 	$v0, print_int		#load label into $v0 for syscall
	syscall

	la	$a0, smallest_msg	#load label into $a0 for syscall
	li	$v0, 4			#load 4 into $v0 for syscall
	syscall
	move	$a0, $s4			#load contents of $s5 into $a0
	li 	$v0, 1			#load 4 into $v0 for syscall
	syscall

exit:
	li	$v0, 10		# Exit the program 
	syscall


biggest_num:
	move	$s5, $s3		# $s5 = contents of $s3 (biggest #)
	b	return1		# branch to return1

smallest_num:
	move $s4, $s3		# $s4 = contents of $s3 (smallest #)
	b	return1		# branch to return1



########## ##DATA#### ########## ##########
	.data

table_msg:		.asciiz	"\n The number of items in the data table is:"

biggest_msg:	.asciiz	"\n     the largest number is :"
smallest_msg:	.asciiz	"\n    the smallest number is :"

print_string:	.word 4	# label used as descriprive alias 
print_int:		.word 1	# label used as descriprive alias

TABLE_SIZE:	.word	9
#TABLE_SIZE:	.word	1

table:	.word	10
		.word	9
		.word	8
		.word	5
		.word	7
		.word	-3
		.word	-15
		.word	18
		.word	2
		.word	999
		.word	998
end_table:	# not necessary when using TABLE_SIZE

table2:	.word	3 -1 6 5 7 -3 -15 18 2 10 11 12 13
table3:	.word	3

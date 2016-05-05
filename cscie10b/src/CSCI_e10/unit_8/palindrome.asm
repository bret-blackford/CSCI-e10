# modified by:	M Bret Blackford
#			ID: 20849347
# CSCI e-10b  Spring 2016
#
# palindrome.asm -- reads a line of text and tests whether it is a palindrome.
## Register usage:
##	$t1	- A.
##	$t2	- B.
##	$t3	- the character *A.
##	$t4	- the character *B.
##	$v0	- syscall parameter / return values.
##	$a0	- syscall parameters.
##	$a1	- syscall parameters.

	.globl  test_loop
	.globl  length_loop
	.globl  string_space
	.text
main:				      # SPIM starts by jumping to main.

	################################################
	## BELOW ADDED BY M BRET BALCKFORD #############

	la	  $a0, start_msg	# provide info to user
	li	  $v0, 4
	syscall

	## ABOVE ADDED BY M BRET BLACKFORD #############
	################################################

					   	# read the string S:
	la      $a0, string_space
	li      $a1, 1024
	li      $v0, 8	       # load "read_string" code into $v0.
	syscall	

	la      $t1, string_space	# initialize $t1 to point to the start

	la      $t2, string_space	# we need to move B to the end
length_loop:			    	#	of the string:
	lb	$t3, ($t2)	    	# load the byte *B into $t3.
	beqz	$t3, end_length_loop   # if $t3 == 0, branch out of loop.
	addu	$t2, $t2, 1	 		# otherwise, increment B,
	b	length_loop		#  and repeat the loop.
end_length_loop:
	subu	$t2, $t2, 2	   	# subtract 2 to move B back past
				          	#  the '\0' and '\n'.
test_loop:
	bge     $t1, $t2, is_palin	# if A >= B, it is a palindrome.

	lb      $t3, ($t1)        	# load the byte *A into $t3,
	lb      $t4, ($t2)	    	# load the byte *B into $t4.

	################################################
	## BELOW ADDED BY M BRET BALCKFORD #############

	li	$t5, 'A'		# load char 'A' into $t5
	blt	$t3, $t5, A_not_char	# if $t3 < ‘A’ branch
	li	$t5, 'z'		# load char 'z' into $t5
	bgt	$t3, $t5, A_not_char 	# if $t3 > 'z' branch

	li	$t5, 'A'		# load char 'A' into $t5
	blt	$t4, $t5, B_not_char
	li	$t5, 'z'		# load char 'z' into $t5
	bgt	$t4, $t5, B_not_char

	li	$t5, 'Z'		# load char 'A' into $t5
	ble	$t3, $t5, A_to_lower
now_lower:
	ble	$t4, $t5, B_to_lower

	## ABOVE ADDED BY M BRET BLACKFORD #############
	################################################

	bne     $t3, $t4, not_palin	# if $t3 != $t4, not a palindrome.
						# Otherwise,
	addu	$t1, $t1, 1          	#  increment A,
	subu	$t2, $t2, 1          	#  decrement B,
	b	test_loop        	#  and repeat the loop.

is_palin:	           	# print the is_palin_msg, and exit.
	la         $a0, is_palin_msg
	li         $v0, 4
	syscall
	b          exit

not_palin:
	la         $a0, not_palin_msg  # print the is_palin_msg, and exit.
	li         $v0, 4
	syscall

exit:			            	# exit the program:
	li		$v0, 10	      # load "exit" into $v0.
	syscall			      # make the system call.


	################################################
	## BELOW ADDED BY M BRET BALCKFORD #############

A_not_char:
	add		$t1, $t1, 1	# increment A (go to next char)
	b		test_loop	# branch back to test_loop

A_to_lower:
	add		$t3, 32		# convert uppercase A to lower
	b		now_lower	# branch to now_lower

B_not_char:
	sub		$t2, $t2, 1	# decrement B (go to next char)
	b		test_loop	# branch to test_loop

B_to_lower:
	add		$t4, 32		# convert uppercase A to lower
	b		now_lower	# branch to now_lower

	## ABOVE ADDED BY M BRET BLACKFORD #############
	################################################
	


## Here is where the data for this program is stored:
	.data
string_space:	.space 1024  # set aside 1024 bytes for the string.
is_palin_msg:	.asciiz "\nThe string is a palindrome.\n\n"
not_palin_msg:	.asciiz "\nThe string is not a palindrome.\n\n"
not_char_msg:	.asciiz " is not a character. \n"
start_msg:	.asciiz "\nEnter a word or sentence and I will tell you if it is a palindrom. \n"


## end of palindrome.asm



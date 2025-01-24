section .data
    prompt_option db "Enter option (1-7): ", 0
    prompt_num1 db "Enter first number: ", 0
    prompt_num2 db "Enter second number: ", 0
    result_msg db "Result: ", 0
    newline db 10, 0

    option dd 0
    num1 dd 0
    num2 dd 0
    result dd 0

section .bss
    input_buffer resb 32

section .text
global _start

_start:
    ; Print option prompt
    mov eax, 4
    mov ebx, 1
    mov ecx, prompt_option
    mov edx, 22
    int 0x80

    ; Read option
    mov eax, 3
    mov ebx, 0
    mov ecx, input_buffer
    mov edx, 32
    int 0x80

    ; Convert option to integer and store
    mov eax, input_buffer
    call atoi
    mov [option], eax

    ; Print first number prompt
    mov eax, 4
    mov ebx, 1
    mov ecx, prompt_num1
    mov edx, 21
    int 0x80

    ; Read first number
    mov eax, 3
    mov ebx, 0
    mov ecx, input_buffer
    mov edx, 32
    int 0x80

    ; Convert first number to integer and store
    mov eax, input_buffer
    call atoi
    mov [num1], eax

    ; Print second number prompt
    mov eax, 4
    mov ebx, 1
    mov ecx, prompt_num2
    mov edx, 22
    int 0x80

    ; Read second number
    mov eax, 3
    mov ebx, 0
    mov ecx, input_buffer
    mov edx, 32
    int 0x80

    ; Convert second number to integer and store
    mov eax, input_buffer
    call atoi
    mov [num2], eax

    ; Perform operation based on option
    mov eax, [option]
    cmp eax, 1
    je do_addition
    cmp eax, 2
    je do_subtraction
    ; Add more comparisons for other operations

do_addition:
    mov eax, [num1]
    add eax, [num2]
    mov [result], eax
    jmp print_result

do_subtraction:
    mov eax, [num1]
    sub eax, [num2]
    mov [result], eax
    jmp print_result

    ; Add more operation implementations here

print_result:
    ; Print result message
    mov eax, 4
    mov ebx, 1
    mov ecx, result_msg
    mov edx, 8
    int 0x80

    ; Convert result to string and print
    mov eax, [result]
    call itoa
    mov ecx, eax
    mov eax, 4
    mov ebx, 1
    mov edx, 32
    int 0x80

    ; Print newline
    mov eax, 4
    mov ebx, 1
    mov ecx, newline
    mov edx, 1
    int 0x80

    ; Exit program
    mov eax, 1
    xor ebx, ebx
    int 0x80

; Function to convert ASCII to integer
atoi:
    ; Implementation omitted for brevity
    ret

; Function to convert integer to ASCII
itoa:
    ; Implementation omitted for brevity
    ret
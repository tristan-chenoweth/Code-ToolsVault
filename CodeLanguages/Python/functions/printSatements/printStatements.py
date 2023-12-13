# this file contains all useful print statement information for python

text = "testing text"
number = 8
array = [1,2,3]

# The following line prints a plain 
print("Printing plain text")

# The following line prints a variable storing text
# It can also be use to print numbers and arrays
print(text)
print(number)
print(array)

# The following lines show how to handle inline line breaks

print("This line has a \nline break")
print("This line has a line break at the end that has been stripped before printing\n".rstrip())
print("\nThis line has a line break at the begining that has been stripped before printing".lstrip())
print("\nThis line has a line break at the begining and the end that has been stripped before printing\n".strip())

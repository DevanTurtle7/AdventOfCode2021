
def parse_input(filename):
    data = []

    with open (filename) as input_file:
        for line in input_file:
            stripped = line.strip()
            num = int(stripped)
            data.append(num)
    
    return data

def larger_than_prev(nums):
    count = 0;

    for i in range(1, len(nums)):
        if nums[i-1] < nums[i]:
            count += 1
    
    return count


def main():
    data = parse_input('./input.txt')
    print(larger_than_prev(data))

if __name__ == "__main__":
    main()
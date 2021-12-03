
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

def sliding_window(nums):
    count = 0;
    current_window = nums[0] + nums[1] + nums[2]
    prev_window = 0

    for i in range(3, len(nums)):
        prev_window = current_window
        # Shift the window along by 1
        current_window -= nums[i-3]
        current_window += nums[i]

        if prev_window < current_window:
            count += 1
    
    return count


def main():
    data = parse_input('./input.txt')
    print(larger_than_prev(data))
    print(sliding_window(data))

if __name__ == "__main__":
    main()
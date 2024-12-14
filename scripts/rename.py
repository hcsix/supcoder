import os

def rename_files_in_directory(directory):
    for root, dirs, files in os.walk(directory):
        for filename in files:
            if 'ruoyi' in filename:
                new_filename = filename.replace('ruoyi', 'supcoder')
                old_file_path = os.path.join(root, filename)
                new_file_path = os.path.join(root, new_filename)
                os.rename(old_file_path, new_file_path)
                print(f'Renamed: {old_file_path} to {new_file_path}')

if __name__ == "__main__":
    current_directory = os.getcwd()
    rename_files_in_directory(current_directory)

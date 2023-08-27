echo "# CS310" >> README.md
call git init
call git add --all
call git commit -m "first commit"
call git branch -M main
call git remote add origin https://github.com/ikim26/CS310.git
call git push -u origin main
PAUSE
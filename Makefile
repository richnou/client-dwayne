

run: BRANCH:=$$(git rev-parse --abbrev-ref HEAD)
run:
	@echo "Running in screen..."
	@echo "Branch is $(BRANCH)"
	@screen
	
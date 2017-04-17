

run: BRANCH:=$$(git rev-parse --abbrev-ref HEAD)
run:
	@echo "Running in screen..."
	@echo "Branch is $(BRANCH)"
	@screen -S $(BRANCH) mvn scala:run -DmainClass=com.dwaynecote.DwayneCote
	
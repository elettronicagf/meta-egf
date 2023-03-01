# Eseguire questo script da sources/meta-egf/
. ./meta-egf-common/scripts/egf-common.sh

# Update Submodules
# (TO DO)

# Applying patches
egf_setup_patches

# Create links in Yocto Root
egf_setup_links

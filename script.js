document.addEventListener('DOMContentLoaded', function () {
    const sections = document.querySelectorAll('.fade-in');
    const revealSection = () => {
        const scrollY = window.pageYOffset;
        sections.forEach(section => {
            const sectionTop = section.offsetTop - window.innerHeight / 1.3;
            if (scrollY > sectionTop) {
                section.classList.add('visible');
            }
        });
    };
    window.addEventListener('scroll', revealSection);
    revealSection();
});

const initialState = {
    notes: [],
    papers: [
        {
            "id" : "58283e77890e0e07e89c1b17",
            "arxivId" : "1609.00055",
            "title" : "Reconstruction of Static Black Hole Images Using Simple Geometric Forms",
            "abstractContent" : "General Relativity predicts that the emission close to a black hole must be lensed by its strong gravitational field, illuminating the last photon orbit. This results in a dark circular area known as the black hole 'shadow'. The Event Horizon Telescope (EHT) is a (sub)mm VLBI network capable of Schwarzschild-radius resolution on Sagittarius A* (or Sgr A*), the 4 million solar mass black hole at the Galactic Center. The goals of the Sgr A* observations include resolving and measuring the details of its morphology. However, EHT data are sparse in the visibility domain, complicating reliable detailed image reconstruction. Therefore, direct pixel imaging should be complemented by other approaches. Using simulated EHT data from a black hole emission model we consider an approach to Sgr A* image reconstruction based on a simple and computationally efficient analytical model that produces images similar to the synthetic ones. The model consists of an eccentric ring with a brightness gradient and a two-dimensional Gaussian. These elemental forms have closed functional representations in the visibility domain, which lowers the computational overhead of fitting the model to the EHT observations. For model fitting we use a version of the Markov chain Monte-Carlo (MCMC) algorithm based on the Metropolis-Hastings sampler with replica exchange. Over a series of simulations we demonstrate that our model can be used for determining geometric measures of a black hole, thus providing information on the shadow size, linking General Relativity with accretion theory.",
            "category" : {
                "_id" : null,
                "name" : "Astrophysics > Instrumentation and Methods for Astrophysics"
            }
        },
        {
            "id" : "58283e8e890e0e07e89c1b34",
            "arxivId" : "1609.00084",
            "title" : "Gaussian complex zeros on the hole event: the emergence of a forbidden region",
            "abstractContent" : "We consider the Gaussian Entire Function (GEF) whose Taylor coefficients are independent complex-valued Gaussian variables, and the variance of the kth coefficient is 1/k!. This random Taylor series is distinguished by the invariance of its zero set with respect to the isometries of the complex plane. We show that the law of the zero set, conditioned on the GEF having no zeros in a disk of radius r, and properly normalized, converges to an explicit limiting Radon measure in the plane, as r goes to infinity. A remarkable feature of this limiting measure is the existence of a large 'forbidden region' between a singular part supported on the boundary of the (scaled) hole and the equilibrium measure far from the hole.",
            "category" : {
                "_id" : null,
                "name" : "Mathematics > Complex Variables"
            }
        },
        {
            "id" : "58283f31890e0e07e89c1c14",
            "arxivId" : "1609.00308",
            "title" : "Shedding light onto topological insulator beads: perspectives for optical tweezing application",
            "abstractContent" : "The interaction of electromagnetic radiation with a spheric-type three-dimensional topological insulator (TI) bead is described within classical optics framework. By virtue of the topological magnetoelectric effect (TMEE) experienced by reflected and transmitted rays at the TI surface, there appears a net constant force on the spherical bead which is proportional to the fine structure constant times the incident radiation power. Such an uniform dynamics (constant acceleration) may be particularly useful for optical tweeezing techniques, for instance, to investigate a DNA strip or a membrane piece under stretching as well as to displace a tiny object by means of purely optical control.",
            "category" : {
                "_id" : null,
                "name" : "Condensed Matter > Mesoscale and Nanoscale Physics"
            }
        },
        {
            "id" : "58283f49890e0e07e89c1c35",
            "arxivId" : "1609.00341",
            "title" : "Linear Convergence of Projection Algorithms",
            "abstractContent" : "Projection algorithms are well known for theirs simplicity and flexibility in solving feasibility problems. They are particularly important in practice since softwares involving projection algorithms require minimal implementation and maintenance. In this paper, we study linear convergence of several projection algorithms for systems of finitely many closed sets. The results complement contemporary research on the same topic.",
            "category" : {
                "_id" : null,
                "name" : "Mathematics > Optimization and Control"
            }
        }

    ]
};

export default initialState;